FROM mongo

#ports
EXPOSE 8080

#Get repositories for java8
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
RUN apt-get update

#Install JDK 8
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections
RUN apt-get install oracle-java8-installer -y

#Install Git
RUN apt-get install git -y

#Install maven
RUN apt-get install maven -y

#Get the source repository
RUN git clone https://github.com/GruppoPBDMNG-2/ShrinkYoUrlTest

#move the client files to the root
#RUN ls ShrinkYoUrlTest/src/main/resources/public
RUN mv /ShrinkYoUrlTest/src/main/resources/public /

#create the start server file and make it executable
RUN echo '#!/bin/bash' >> /start-server
RUN echo 'cd ShrinkYoUrlTest/src/main/java' >> /start-server
RUN echo 'mvn package' >> /start-server
RUN echo 'java -jar target/ShrinkYoUrl.jar' >> /start-server
RUN chmod 755 /start-server
