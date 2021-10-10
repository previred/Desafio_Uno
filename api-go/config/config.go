package config

type Backend struct {
	Protocol string
	Host     string
	Port     int
	Root     string
}

type AppConfig struct {
	Protocol string
	Host     string
	Port     int
	Root     string
	Backend  Backend
}

func Config() *AppConfig {
	return &AppConfig{
		Protocol: "HTTP",
		Host:     "localhost",
		Port:     8090, Root: "/",
		Backend: Backend{
			Protocol: "HTTP",
			Host:     "localhost",
			Port:     8080, Root: "/periodos",
		},
	}
}
