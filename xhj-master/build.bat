::设置脚本的工作目录
cd /d %~dp0
cd ../xhj-parent
::设置要构建的模块名。可多个模块同时构建，模块名间用逗号隔开。
call mvn install -pl ../xhj-master/ -am
@pause