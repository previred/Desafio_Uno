package routes

import (
	"desafiouno/controller"
	"encoding/json"
	"log"
	"net/http"
)

type Router struct {
}

func (t Router) Periodos(w http.ResponseWriter, r *http.Request) {

	ctl, err := controller.NewPeriodosController()
	if err != nil {
		log.Fatal(err)
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	response, err := ctl.GetPeriodos()
	if err != nil {
		log.Fatal(err)
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	jData, err := json.Marshal(response)
	if err != nil {
		log.Fatal(err)
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	w.Header().Set("Content-Type", "application/json")
	w.Write(jData)
}
