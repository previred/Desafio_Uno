package services

import (
	"desafiouno/datasource"
	"desafiouno/types"
	"log"
	"time"

	openapi_types "github.com/deepmap/oapi-codegen/pkg/types"
)

const (
	layoutISO = "2006-01-02"
)

type PeriodosService struct {
	ds *datasource.DatasourceRest
}

func NewPeriodosService() (*PeriodosService, error) {
	ds, err := datasource.NewDatasourceRest()
	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	return &PeriodosService{ds: ds}, nil
}

func (s *PeriodosService) GetPeriodos() (*types.Periodo, error) {

	response, err := s.ds.GetPeriodos()
	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	fromDate, err := time.Parse(layoutISO, response.FechaCreacion.String())
	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	toDate, err := time.Parse(layoutISO, response.FechaFin.String())
	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	allDates := map[string]string{}

	for ok := true; ok; ok = !fromDate.After(toDate) {
		key := fromDate.Format(layoutISO)
		allDates[key] = fromDate.Format(layoutISO)
		fromDate = fromDate.AddDate(0, 1, 0)
	}

	dates := map[string]string{}
	for _, item := range *response.Fechas {
		f, err := time.Parse(layoutISO, item.String())
		if err != nil {
			log.Fatal(err)
			return nil, err
		}
		key := f.Format(layoutISO)
		dates[key] = key
	}

	missingDates := []openapi_types.Date{}

	for key := range allDates {
		if _, exists := dates[key]; !exists {
			f, err := time.Parse(layoutISO, allDates[key])
			if err != nil {
				log.Fatal(err)
				return nil, err
			}
			missingDates = append(missingDates, openapi_types.Date{Time: f})
		}
	}

	response.FechasFaltantes = &missingDates
	return response, nil

}
