defmodule DomainTest do
  use ExUnit.Case
  doctest Domain

  test "that returns correct dates from 1969 to 1971" do
    input = %{
      "id" => 7,
      "fechaCreacion" => "1969-03-01",
      "fechaFin" => "1970-01-01",
      "fechas" => [
        "1969-03-01",
        "1969-05-01",
        "1969-09-01",
        "1970-01-01"
      ]
    }

    expected = %{
      :id => 7,
      :fechaCreacion => "1969-03-01",
      :fechaFin => "1970-01-01",
      :fechasFaltantes => [
        "1969-04-01",
        "1969-06-01",
        "1969-07-01",
        "1969-08-01",
        "1969-10-01",
        "1969-11-01",
        "1969-12-01"
      ]
    }

    assert Domain.solve(input) == expected
  end

  test "that returns correct dates from 1971 to 1969" do
    input = %{
      "id" => 7,
      "fechaCreacion" => "1970-01-01",
      "fechaFin" => "1969-03-01",
      "fechas" => [
        "1969-03-01",
        "1969-05-01",
        "1969-09-01",
        "1970-01-01"
      ]
    }

    expected = %{
      :id => 7,
      :fechaCreacion => "1970-01-01",
      :fechaFin => "1969-03-01",
      :fechasFaltantes => [
        "1969-04-01",
        "1969-06-01",
        "1969-07-01",
        "1969-08-01",
        "1969-10-01",
        "1969-11-01",
        "1969-12-01"
      ]
    }

    assert Domain.solve(input) == expected
  end
end
