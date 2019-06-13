FROM java:openjdk-8-jdk
MAINTAINER PALINDROME

# Make sure the package repository is up to date.
RUN apt-get update && apt-get -y upgrade
# Install a basic SSH server
RUN apt-get install -y openssh-server &&   sed -i 's|session    required     pam_loginuid.so|session    optional     pam_loginuid.so|g' /etc/pam.d/sshd && mkdir -p /var/run/sshd

# Add user jenkins to the image
RUN adduser --quiet jenkins
# Set password for the jenkins user (you may want to alter this).
RUN echo "jenkins:jenkins" | chpasswd

RUN wget -q https://services.gradle.org/distributions/gradle-3.3-bin.zip \
    && unzip gradle-3.3-bin.zip -d /opt \
    && rm gradle-3.3-bin.zip

ENV GRADLE_HOME /opt/gradle-3.3
ENV PATH $PATH:/opt/gradle-3.3/bin

# Standard SSH port
EXPOSE 22

CMD ["/usr/sbin/sshd", "-D"]
