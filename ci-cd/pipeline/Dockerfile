FROM ubuntu:20.04
MAINTAINER sankarsana

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update && \
    apt -y install \
        unzip \
        curl \
        wget \
        bzip2 \
        git \
        gcc \
        openssh-client \
        ca-certificates \
        acl \
        sudo \
        telnet \
        openjdk-8-jdk && \
    apt clean && \
    apt purge && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

ENV LANG C.UTF-8
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/

# ----------------- Android SDK -----------------
ENV ANDROID_HOME /opt/android-sdk

# https://developer.android.com/studio/index.html#command-tools
ARG ANDROID_NDK_VERSION=21.3.6528147
ARG ANDROID_CMAKE_VERSION=3.6.4111459
ARG ANDROID_PLATFORM_VERSION=30
ARG ANDROID_BUILD_TOOLS_VERSION=30.0.2
ARG ANDROID_SDK_URL=https://dl.google.com/android/repository/commandlinetools-linux-6858069_latest.zip
ARG ANDROID_SDK_FILE_NAME=android-sdk.zip

RUN wget -nc $ANDROID_SDK_URL -O $ANDROID_SDK_FILE_NAME && \
    mkdir -p $ANDROID_HOME && \
    unzip -q $ANDROID_SDK_FILE_NAME -d $ANDROID_HOME/ && \
    rm $ANDROID_SDK_FILE_NAME

ENV PATH ${PATH}:${ANDROID_HOME}/tools/:${ANDROID_HOME}/tools/bin:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/platform-tools/bin:${ANDROID_HOME}/cmdline-tools/bin

# Update sdk and install components
# --package_file is broken https://issuetracker.google.com/issues/66465833
RUN mkdir $HOME/.android && \
  # empty file to mitigate warning
  touch $HOME/.android/repositories.cfg && \
  yes | sdkmanager --licenses --sdk_root="${ANDROID_HOME}"

RUN echo "y" | sdkmanager --verbose \
    --sdk_root="${ANDROID_HOME}" \
    "ndk;$ANDROID_NDK_VERSION" \
    "cmake;$ANDROID_CMAKE_VERSION" \
    "build-tools;$ANDROID_BUILD_TOOLS_VERSION" \
    "platforms;android-$ANDROID_PLATFORM_VERSION" && \
    sdkmanager --update --sdk_root="${ANDROID_HOME}"

# ----------------- Permissions -----------------
RUN chmod -R o+rwX ${ANDROID_HOME} && \
    chown -R root:root $ANDROID_HOME && \
    groupadd -g 1000 -r jenkins && \
    groupadd -g 999 -r docker && \
    groupadd -g 994 -r dockerroot && \
    useradd --create-home -d "/home/jenkins" -u 1000 -r -g jenkins -G docker,dockerroot jenkins

USER jenkins