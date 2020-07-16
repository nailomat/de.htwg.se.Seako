FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1
WORKDIR /seako
ADD . /seako
RUN apt-get update -y
RUN apt-get install -y libxext-dev
RUN apt-get install -y libxrender-dev
RUN apt-get install -y libxtst6
ENV DISPLAY :0
CMD sbt run