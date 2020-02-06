#
# Ngnix Dockerfile
#
FROM ubuntu 16.04

LABEL Description "Ngnix" vendor "deepak" version "1.0"

# Install Ngnix.
RUN apt-get update && apt-get install -y software-properties-common && \
add-apt-repository -y ppa:nginx/stable && \
apt-get update && \
apt-get install -y nginx && \
rm -rf /var/lib/apt/list/* && \
echo "\ndaemon off;" >> /etc/nginx/nginx.conf && \
chown -R www-data:www-data /var/lib/nginx

#define mountable directories.
VOLUMN ["/etc/nginx/sites-enabled", "/etc/nginx/certs","/etc/nginx/conf.d","/var/log/nginx",".\/var/www/html"]

#define working directory

WORKDIR /etc/nginx/certs

# define default command

CMD ["nginx"]

#expose ports.
EXPOSE 80
EXPOSE 443