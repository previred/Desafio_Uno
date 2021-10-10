package controller

import (
	"desafiouno/services"
	"desafiouno/types"
	"log"
)

type PeriodosController struct {
	service *services.PeriodosService
}

func NewPeriodosController() (*PeriodosController, error) {
	service, err := services.NewPeriodosService()
	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	return &PeriodosController{service: service}, nil
}

func (s *PeriodosController) GetPeriodos() (*types.Periodo, error) {

	response, err := s.service.GetPeriodos()
	if err != nil {
		log.Fatal(err)
		return nil, err
	}
	return response, nil

}
