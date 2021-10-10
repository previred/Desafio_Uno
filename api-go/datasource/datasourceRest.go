package datasource

import (
	"desafiouno/config"
	"desafiouno/types"
	"errors"
	"fmt"
	"log"

	resty "github.com/go-resty/resty/v2"
)

type DatasourceRest struct {
	endpoint string
	client   *resty.Client
}

func NewDatasourceRest() (*DatasourceRest, error) {
	config := config.Config().Backend

	endpoint := fmt.Sprintf("%s://%s:%d", config.Protocol, config.Host, config.Port)

	client := resty.New()

	return &DatasourceRest{endpoint: endpoint, client: client}, nil
}

func (s *DatasourceRest) GetPeriodos() (*types.Periodo, error) {

	response := &types.Periodo{}

	config := config.Config().Backend

	endpoint := fmt.Sprintf("%s://%s:%d%s/api", config.Protocol, config.Host, config.Port, config.Root)

	resp, err := s.client.R().SetHeader("Accept", "application/json").
		SetResult(&response).
		Get(endpoint)
	if err != nil {
		log.Fatal(err)
		return nil, err
	}

	if resp.IsError() {
		log.Fatal(resp.String())
		return nil, errors.New(resp.String())
	}
	return response, nil

}
