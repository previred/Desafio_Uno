#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:5.0 AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:5.0 AS build
WORKDIR /src
COPY ["DesafioUno.GDD.API/DesafioUno.GDD.API.csproj", "DesafioUno.GDD.API/"]
RUN dotnet restore "DesafioUno.GDD.API/DesafioUno.GDD.API.csproj"
COPY . .
WORKDIR "/src/DesafioUno.GDD.API"
RUN dotnet build "DesafioUno.GDD.API.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "DesafioUno.GDD.API.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "DesafioUno.GDD.API.dll"]