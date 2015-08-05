### Base Docker Image

* [dockerfile/ubuntu](http://dockerfile.github.io/#/ubuntu)


### Installation

1. Install [Docker](https://www.docker.com/).

2. Download [automated build](https://registry.hub.docker.com/u/dockerfile/mongodb/) from public [Docker Hub Registry](https://registry.hub.docker.com/): `docker pull dockerfile/mongodb`

   (alternatively, you can build an image from Dockerfile: `docker build -t="dockerfile/mongodb" github.com/dockerfile/mongodb`)


### Usage

    docker build -t gruppo_pbdmng-2/shrinkyourl .
    docker run -d -p 8080:8080 -p 27017:27017 --name=shrinkyourl gruppo_pbdmng-2/shrinkyourl
    docker exec -it shrinkyourl bash
    ./start
    ./test
   

##### Usage with VirtualBox (boot2docker-vm)

_You will need to set up nat port forwarding with:_  

    VBoxManage modifyvm "boot2docker-vm" --natpf1 "guestmongodb,tcp,127.0.0.1,27017,,27017"

This will allow you to connect to your mongo container with the standard `mongo` commands.
