@echo off
set who=%~1
if "%who%" == "github" (
 git remote set-url origin git@github.com:tonsonbin/clientDemo.git
) ^
else if "%who%" == "aliyun" (
 git remote set-url origin git@code.aliyun.com:tangbin/clientDemo.git
)
git remote -v