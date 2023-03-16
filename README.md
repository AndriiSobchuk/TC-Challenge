# TC-Challenge
![CI_CD pipeline AS](https://user-images.githubusercontent.com/86925275/225771390-895ce56d-2e41-48ff-9453-978e75d55c3a.jpeg)

This repository contains a simple CI/CD Pipeline for dockerized Java Spring Boot project. 

To build the project locally just simply clone this repo and run provided Dockerfile to build the image:
`docker build -t <your-tag> . `
`docker run -p 8080:8080 -it <your-tag>`
OR use docker-compose.yaml (just set your own tag or upload image commented image from dockerhub) with `docker-compose up .`
  
CI/CD pipeline based on Jenkins and polls SCM every minute by schedule (by also possible to setup a Webhooks) from branch `/main` and in case of  any changes it triggers `docker build` command to build an image and then simply login and push it dockerhub repository, then we deploy our application to K8S and finally logout from dockerhub inside the job.

Below you can find some detailed screens:


![screenshot 164](https://user-images.githubusercontent.com/86925275/225774186-c80dd1b2-62ec-41de-8f5e-967c7af165a5.jpg)
![screenshot 165](https://user-images.githubusercontent.com/86925275/225774195-5fa93b07-417f-4284-88c4-645bc35cdc23.jpg)
![screenshot 166](https://user-images.githubusercontent.com/86925275/225774293-7975f31d-f0e4-4d59-bc9e-093ba08af032.jpg)

**Just a few notes**
1. It's important to add versioning for our builds and store them somewhere (Artifactory, Nexus, etc.)
2. It's important to add versioning for our docker images in case rollback will be required
3. We could add extra steps to out pipeline: tests, successful deployment to stage, pre-prod envs before we will go to prod
4. We could add some scaling policies to our app deployment to make it more reliable and fault-tolerant
5. Any other improvements possible :)
