FROM ubuntu:18.04

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US.UTF-8

RUN apt-get update -y && \
    apt-get install -y python3-pip python3-dev  &&\
    apt-get install -y locales

RUN locale-gen en_US.UTF-8

RUN export LC_ALL=${LC_ALL}
RUN export LANG=${LANG}

# We copy just the requirements.txt first to leverage Docker cache
COPY ./bin/requirements.txt /app/requirements.txt

WORKDIR /app

RUN pip3 install -r requirements.txt

COPY ./src/ /app

RUN export FLASK_APP=app.py

ENTRYPOINT [ "flask" ]

CMD [ "run","--host","0.0.0.0"]