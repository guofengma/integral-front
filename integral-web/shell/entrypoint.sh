TOMCAT_HOME="/data/tomcat7"

if [[ $ENV = "docker-uat" ]]; then
echo $ENV
sh /data/replace-uat-domains.sh
sh /data/add-uat-hosts.sh
rm -rf "${TOMCAT_HOME}"/webapps/ROOT/.idea
else
echo "product"
fi
echo $ENV
"${TOMCAT_HOME}"/bin/startup.sh ;tail -f /data/tomcat7/logs/catalina.out