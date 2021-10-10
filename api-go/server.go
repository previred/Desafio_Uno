package main

import (
	"context"
	"desafiouno/config"
	"desafiouno/openapi"
	"desafiouno/routes"
	"fmt"
	"log"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"
)

func main() {
	s := routes.Router{}
	h := openapi.Handler(s)

	config := config.Config()

	endpoint := fmt.Sprintf("%s:%d", config.Host, config.Port)

	// Shutdown channels for Shutdown hook
	errChannel := make(chan error)
	stopChannel := make(chan os.Signal)
	// Termination Signal (Linux/ Windows)
	signal.Notify(stopChannel, syscall.SIGTERM, syscall.SIGINT)

	// Start

	// Start
	server := &http.Server{
		Addr:    endpoint,
		Handler: h,
	}

	go func() {
		log.Printf("API Server started")
		log.Printf("Listening at: [http://%s:%d%s]", config.Host, config.Port, config.Root)
		if err := server.ListenAndServe(); err != nil {
			errChannel <- err
			return
		}
	}()

	// Shutdown hook: terminate environment gracefully before leaving main function
	defer func() {
		ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
		defer cancel()

		server.Shutdown(ctx)
		// Any other cleanup logic goes here
	}()

	// Shutdown hook: block until either OS signal, or server fatal error
	select {
	case err := <-errChannel:
		log.Printf("Fatal error: %v\n", err)
	case <-stopChannel:
		log.Printf("Received kill signal, closing pending connections")
	}
}
