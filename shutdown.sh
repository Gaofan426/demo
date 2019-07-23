#!/usr/bin/env bash

#得到进程ID pid，kill该进程
pid=`cat pid`
if [ -n "$pid" ]
then
    echo "kill -9 的pid:" $pid
    kill -9 $pid
fi
echo "demo服务关闭成功"
