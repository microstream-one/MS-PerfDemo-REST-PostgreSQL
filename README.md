# MS-PerfDemo-REST-PostgreSQL

<img width="1309" alt="PerfDemoArchitecture" src="https://github.com/user-attachments/assets/40a4d1d5-ccc5-47b8-a867-6de278f89bf7" />

REST Proxy receives the the requests from sender and returns PostgreSQL data. On the architecture chart this project is the yellow section on the right.

## Setup

1. Build an image by using the following command:
   ```Clean package -D packaging=docker```
3. Copy that image to your docker environment
4. Use the docker compose file from sender project here to deploy whole infrastructure:
   https://github.com/microstream-one/MS-PerfDemo-Sender/tree/master/Microstream-Config/performance-demo
