sed -i "s/.lenovo.com.cn/.lenovouat.cn/g" `grep .lenovo.com.cn -rl /data/tomcat7/webapps/ROOT`
sed -i "s/.lenovovip.com.cn/.vip.lenovouat.cn/g" `grep  .lenovovip.com.cn -rl /data/tomcat7/webapps/ROOT`
sed -i "s/.thinkworldshop.com.cn/.tks.lenovouat.cn/g" `grep  .thinkworldshop.com.cn -rl /data/tomcat7/webapps/ROOT`

echo "replase finsh"