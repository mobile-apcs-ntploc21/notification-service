# Orantio - A Mobile Messaging Application - Notification Service

Orantio is a mobile messaging platform, designed to enhance your communication experience through seamless connectivity and powerful features. Whether you're chatting one-on-one or collaborating in large groups, our platform provides an intuitive and secure space for all your conversations. With real-time online presence and support for multiple devices, you'll never miss a moment—stay connected effortlessly across all your favorite devices.

> This is the final group project from two courses in VNUHCM - University of Science - CS426 - Mobile Device Application Development and CS300 - Introduction to Software Engineering.

![image](https://github.com/user-attachments/assets/81a1a1a5-3806-40c1-8a49-928236ac4e5d)

For a full documentation of the project, please refer to the README of the [Mobile Backend](https://github.com/mobile-apcs-ntploc21/mobile-backend) repository.

## Installation (Backend System)

### Docker Installation

Ensure you have Docker installed on your machine. If not, you can download it from [Docker](https://www.docker.com/).

1. Clone the repository:
```bash
git clone https://github.com/mobile-apcs-ntploc21/mobile-backend.git
```

2. At the root folder, you need to make a file called .env from the provided template .env.template and fill in the necessary information.

3. To enable the notification feature of Firebase Cloud Messaging, you need to provide the `google-services.json` file at the root folder and file `fcm-key.json` at folder `src/main/resources`.

4. Build the Docker image:
```bash
docker-compose build
```

5. Start the Docker container:
```bash
docker-compose up
```
Note: You need to run the [`notification-service`](https://github.com/mobile-apcs-ntploc21/notification-service) following the Docker installation guide to enable the notification feature before running the [`mobile-backend`](https://github.com/mobile-apcs-ntploc21/mobile-backend).

## Contributor

The project could not have been completed without these developers!

- 22125050 - Nguyễn Thanh Phước Lộc
    - ntploc22@apcs.fitus.edu.vn
- 22125068 - Trương Chí Nhân
    - tcnhan22@apcs.fitus.edu.vn
- 22125076 - Nguyễn Hoàng Phúc
    - nhphuc221@apcs.fitus.edu.vn
- 22125115 - Ngô Hoàng Tuấn
    - nhtuan22@apcs.fitus.edu.vn
- 22125121 - Đinh Hoàng Việt
    - dhviet22@apcs.fitus.edu.vn
