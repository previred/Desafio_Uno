defmodule Domain do
  @format "%Y-%m-%d"
  @max_items 100
  @formatter Timex.Format.DateTime.Formatters.Strftime

  def generate_dates(_, _, all_dates, true) do
    all_dates
  end

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

  def generate_dates(start_at, end_at, all_dates \\ [], done \\ false) do
    # // TODO: Consider the Use Case of Greater Start than End Date
    # 0: when equal
    # -1: when the first date/time comes before the second
    # 1: when the first date/time comes after the second
    # if start_at > end_at do
    #   IO.puts("Start is greater than end")
    #   temp = start_at
    #   start_at = end_at
    #   end_at = temp
    # end

    start_str = @formatter.format!(start_at, @format)
    end_str = @formatter.format!(end_at, @format)

    all_dates = all_dates ++ [start_str]
    all_dates = all_dates ++ [end_str]

    generate_dates(
      Timex.shift(start_at, months: 1),
      end_at,
      all_dates,
      start_str == end_str
    )
  end

  def start(args) do
    file_input = Enum.at(args, 2) || "input.json"
    file_output = Enum.at(args, 3) || "output.json"

    params = Jason.decode!(File.read!(file_input))

    with {:ok, start_at} <- Timex.parse(Map.fetch!(params, "fechaCreacion"), @format, :strftime),
         do:
           with(
             {:ok, end_at} <- Timex.parse(Map.fetch!(params, "fechaFin"), @format, :strftime),
             do: generate_dates(start_at, end_at) |> IO.inspect()
           )
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
