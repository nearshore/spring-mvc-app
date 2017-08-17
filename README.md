# Spring MVC App

This repository contains the code base for Spring MVC based application which could be used as a template for creating new applications or training.

The information below describes the steps to follow and setup a development environment into Unix or windows base system.

## Overview

This project uses [Vagrant](https://www.vagrantup.com/) and [Docker](https://www.docker.com/) which facilitates setup of developer environment.

[Vagrant](https://www.vagrantup.com/) provides a industry-standard way to configure and reproduce portable work environments.

[Docker](https://www.docker.com/) is a software deployment platform where everything required to make a piece of software to run is packaged into isolated containers.

Essentially, a VM is setup into host system. The base containers images are pull from Docker build-in images from the Docker Hub. The project ones are build based on source code. All the containers are started in Docker platform to provide such services and functions.

## Instructions

### 1. Installing Base Tools

#### Windows

Chocolatey is a great way to manage packages on Windows, the guide provides initial steps using choco command but you can also install each base tool using regular msi windows installer.

* [Vagrant](https://www.vagrantup.com/downloads.html)
* [MSYS2](http://www.msys2.org/)

##### Prerequisites on Windows 7 Professional SP1

Verify your PowerShell (aka 'ps') version. Open a ps console and run the command

```
$PSVersionTable.PSVersion

Major  Minor  Build  Revision
-----  -----  -----  --------
2      0      -1     -1
```
If the number under Major column is lesser than 3, update ps in win7 to v3

* [Download and install Windows6.1-KB2506143-x64.msu](https://www.microsoft.com/en-us/download/details.aspx?id=34595)

After the installation it ask you for a restart.

Verifying ps is installed correctly

```
$PSVersionTable.PSVersion

Major  Minor  Build  Revision
-----  -----  -----  --------
3      0      -1     -1
```

##### Installing Chocolatey

Run the following command from an administrative PowerShell v3+ prompt (Ensure Get-ExecutionPolicy is not Restricted):

```powershell
iwr https://chocolatey.org/install.ps1 -UseBasicParsing | iex
```

Since Restricted is default Execution Policy likely you need to run below Power Shell command to be able to run installer script

```powershell
Set-ExecutionPolicy RemoteSigned
```

For more information read [Chocolatey Installation Instructions](https://chocolatey.org/install).

##### Installing base tools with Chocolatey

Once Chocolatey is installed you can use it to install base tools using Chocolatey. You must as administrator open a cmd or a powershell console to work with Chocolatey commands. Run the following commands:

```cmd
c:\> choco install vagrant virtualbox msys2
```

This will install Vagrant, VirtualBox and MSYS2 packages. You could also use normal installers if you require a special configuration.

##### Install MSYS2 packages

Open *MSYS2* shell and execute the command below to install openssh and git packages into MSYS2 shell.

```bash
pacman -S openssh git
```

After this packages are installed you should be able to execute them from MSYS2 shell.

All listings below assume you are using a Unix shell like MSYS2.

#### Debian and derivatives

Execute command below.

```bash
sudo apt-get install vagrant virtualbox openssh-client git
```


### 2. Fork and clone this repository

Forking this repository is a great approach to keep your own copy of the code and also to contribute to the project by submitting pull requests.

* If you require more information about forking projects see [Forking Projects](https://guides.github.com/activities/forking/) guide.

To clone the repository open a shell and execute command below.

```bash
$ git clone https://username@github.com/username/spring-mvc-app
```

### 3. Use Vagrant to provision the environment

Open a shell (*you could use MSYS2 or ps to run vagrant/docker commands*) and change to your ```spring-mvc-app``` directory, then run ```vagrant up``` command. This will download for the first time and setup a *VirtualBox* image with required tools for your development environment.

```bash
cd spring-mvc-app
$ vagrant up
```

The execution of the command above should start and provision the environment specified at the Vagrant configuration file.

Once completed you can work inside the guest by running the following command to open an SSH connection to the guest host.

```bash
$ vagrant ssh
```
> If you are running this command from ps and it didn't work: Search for ssh.exe on your computer, copy the Path (i.e. C:\Program Files (x86)\Git\bin), open System Preferences, find the Environment variable Settings, click on the Path Variable, add the path, separating the existing paths using ```;```.

### 4. Build and Compose

Run commands below in order to get your project up and running. First you compile the application. In order to do that you need to move to the directory where project files are stored inside the guest, that is the ```/vagrant``` directory, and execute the Gradle build script.

```bash
$ cd /vagrant
$ ./gradlew build
```

> If you ran into the next problem running ```$ ./gradlew build```
> ```
> $ ./gradlew build
> /usr/bin/env: ‘bash\r’: No such file or directory
> ```
> Execute next commands:
> ```
> $ sed $'s/\r$//' ./gradlew > ./gradlew.Unix
> $ ./gradlew.Unix build
> ```

Once compilation is completed a JAR file will be generated, with that you would have everything you need to deploy the solution, which is done using ```docker-compose``` command.

```bash
$ docker-compose up
```

That would generate the containers required to run the application and show the logs on the screen.

Once containers are up you will be able to access the application from a browser on host operating system.

* NGINX: http://localhost:9080 
* Tomcat: http://localhost:8080

Read more about Docker Compose commands on the [reference](https://docs.docker.com/compose/reference/) guide.

