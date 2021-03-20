defmodule Domain do
  @format "%Y-%m-%d"
  @max_items 100
  @formatter Timex.Format.DateTime.Formatters.Strftime

  # if start_at and end_at are equal
  # return the all_dates list
  def generate_dates(_, _, all_dates, true) do
    all_dates
  end

  # if the start and end are not equal
  # lets append the date string to the all_dates list
  # and check for next month
  def generate_dates(start_at, end_at, all_dates, false) do
    start_str = @formatter.format!(start_at, @format)
    end_str = @formatter.format!(end_at, @format)

    all_dates = all_dates ++ [start_str]

    generate_dates(
      Timex.shift(start_at, months: 1),
      end_at,
      all_dates,
      start_str == end_str
    )
  end

  # first iteration, set the initial values
  # and call the next iteration with the next month
  def generate_dates(start_at, end_at, all_dates, _done) do
    # Consider the Use Case of Greater Start than End Date
    should_flip = Timex.compare(start_at, end_at) > 0

    start_at_date = if should_flip, do: end_at, else: start_at
    end_at_date = if should_flip, do: start_at, else: end_at

    start_str = @formatter.format!(start_at_date, @format)
    end_str = @formatter.format!(end_at_date, @format)

    all_dates = all_dates ++ [start_str]
    all_dates = all_dates ++ [end_str]

    generate_dates(
      Timex.shift(start_at_date, months: 1),
      end_at_date,
      all_dates,
      start_str == end_str
    )
  end

  def generate_output(all_dates, params) do
    final_dates =
      Enum.sort(
        MapSet.to_list(
          MapSet.difference(
            MapSet.new(all_dates),
            MapSet.new(Map.fetch!(params, "fechas"))
          )
        )
      )

    %{
      :id => Map.fetch!(params, "id"),
      :fechaCreacion => Map.fetch!(params, "fechaCreacion"),
      :fechaFin => Map.fetch!(params, "fechaFin"),
      :fechasFaltantes => Enum.take(final_dates, @max_items)
    }
  end

  def solve(params) do
    {:ok, start_at} = Timex.parse(Map.fetch!(params, "fechaCreacion"), @format, :strftime)
    {:ok, end_at} = Timex.parse(Map.fetch!(params, "fechaFin"), @format, :strftime)
    generate_dates(start_at, end_at, [], nil) |> generate_output(params)
  end

  def write_solution_to_disk(solution, file_output \\ "output.json") do
    File.write!(file_output, Jason.encode!(solution))
  end

  def solve_with_fileoutput(params, file_output \\ "output.json") do
    solve(params) |> write_solution_to_disk(file_output)
  end

  def start(args) do
    file_input = Enum.at(args, 0) || "input.json"
    file_output = Enum.at(args, 1) || "output.json"

    params = Jason.decode!(File.read!(file_input))

    solve_with_fileoutput(params, file_output)
  end
end

defmodule AppCLI do
  def main(args) do
    Domain.start(args)
  end
end

defmodule Mix.Tasks.Solution do
  def run(args) do
    Domain.start(args)
  end
end
