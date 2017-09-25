# Spring MVC App

This repository contains the Dockerfile, Jenkinsfile and code for Spring MVC application which is used for the Continuous Delivery Proof of Concept.

The information below describes the steps to follow to build the docker image and start the docker container.

## Overview

[Docker](https://www.docker.com/) is a software deployment platform where everything required to make a piece of software to run is packaged into isolated containers.
[Pipeline]A continuous delivery pipeline is an automated expression of your process for getting software from version control right through to your users and customers. Every change to your software (committed in source control) goes through a complex process on its way to being released. 

## Instructions at Jenkins Server

### 1. Create New Item - Select Pipeline
### 2. Select GitHub project: Enter GitHub URL
### 3. Select GitHub hook trigger for GITScm polling
### 5. Select Pipeline script from SCM: Enter GitHub URL
### 5. At Branches to build enter: /testing
### 5. At GitHub select settings, click Webhooks and enter the Jenkins server webhook URL
