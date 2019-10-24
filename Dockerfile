FROM python:latest

COPY ./requirements.txt /app/requirements.txt
RUN pip install --upgrade pip
# Install any needed packages specified in requirements.txt
RUN pip install -r /app/requirements.txt

# Copy the current directory contents into the container at /app/
COPY . /app/
# Set the working directory to /app/
WORKDIR /app/

EXPOSE 5000
