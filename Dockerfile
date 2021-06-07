FROM jenkins/jenkins:lts
MAINTAINER sankarsana

USER root

VOLUME var/jenkins_home

RUN apt update && \
    apt -y install \
        apt-transport-https \
        sudo \
        ca-certificates \
        curl \
        gnupg-agent \
        software-properties-common && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
    add-apt-repository \
        "deb [arch=amd64] https://download.docker.com/linux/debian \
        $(lsb_release -cs) \
        stable" && \
    apt update && \
    apt install -y docker-ce docker-ce-cli containerd.io && \
    usermod -aG docker jenkins && \
    groupadd -g 994 dockerroot && \
    usermod -aG dockerroot jenkins && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
    apt autoremove -y && \
    apt clean

USER jenkins