#!/usr/bin/env bash

#得到进程ID pid，kill该进程
pid=`cat pid`
if [ -n "$pid" ]
then
    echo "kill -9 的pid:" $pid
    kill -9 $pid
fi
echo "demo服务关闭成功"

#执行jar，并将进程挂起，保存进程ID到 pid文件
nohup java -Xms512m -Xmx512m -Xmn128m -jar target/demo-1.0.jar --spring.profiles.active=$1 & echo "$!" > pid
echo "demo服务启动成功"