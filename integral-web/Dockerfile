From dockerhub.lefile.cn/alpine_tomcat:7
MAINTAINER shaoyh2@lenovo.com
ADD target/*.war /data/tomcat7/webapps/ROOT.war
ADD shell/entrypoint.sh /data/
ADD shell/replace-uat-domains.sh /data/
ADD shell/add-uat-hosts.sh /data/
EXPOSE 8080
RUN chmod +x /data/entrypoint.sh
RUN chmod +x /data/replace-uat-domains.sh
RUN chmod +x /data/add-uat-hosts.sh
#ENTRYPOINT ./data/entrypoint.sh
CMD ./data/entrypoint.sh
