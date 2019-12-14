# 玩转机器学习

## 环境的搭建命令如下

> docker run -d -v "$PWD":/home/jovyan/work/  -e GRANT_SUDO=yes  -p  8888:8888  jupyter/tensorflow-notebook start-notebook.sh --NotebookApp.password='sha1:660fe90e901f:9694d60bd4e5b30bfbd0418d1d1f5a7e2e98b2f2'

+ 容器添加sudo权限(允许用户对镜像做修改)：`-e GRANT_SUDO=yes`，参考 https://jupyter-docker-stacks.readthedocs.io/en/latest/using/common.html
+ 给容器设置密码：这里的密码`sha1:660fe90e901f:9694d60bd4e5b30bfbd0418d1d1f5a7e2e98b2f2`，破解后是自己最常用的密码。是使用notebook的auth模块生成的

  ```python
  In [1]: from notebook.auth import passwd
  In [2]: passwd()
  Enter password:
  Verify password:
  Out[2]: 'sha1:67c9e60bb8b6:9ffede0825894254b2e042ea597d771089e11aed'
  ```
  
## 目录
