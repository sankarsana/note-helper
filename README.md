# Notes

## Features:
- Add/edit notes contains:
    - texts
    - lists (coming soon)
    - links (coming soon)
    - images (coming soon)
    - ...
- Synchronizing notes between user devices (coming soon)
- The ability to share the notes with another user (coming soon)
- Granting various permissions to another user to read and modify the self notes (coming soon)

## Technology stack
- Kotlin + Coroutines
- Clean Architecture + MVVM
- Multimodule Gradle Project
- Dagger 2
- Retrofit
- Room Persistence Library
- Unit testing
- ...

## Prepare CI/CD

```bash
    $ cd ci-cd/
    $ docker build -t jenkins-docker .
    $ docker run -it -p 8080:8080 -p 50000:50000 \
        -v $HOME/jenkins:/var/jenkins_home \
        -v /var/run/docker.sock:/var/run/docker.sock \
        --restart unless-stopped --name="jenkins-docker" jenkins-docker
```
