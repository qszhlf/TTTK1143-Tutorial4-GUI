@echo off
setlocal
cd /d "%~dp0"
where javac >nul 2>nul
if errorlevel 1 (
    echo ERROR: Java JDK was not found. Install a JDK and add its bin folder to PATH.
    pause
    exit /b 1
)
for %%D in (DataSetApp EdibleApp MobileApp PriceApp) do (
    echo Compiling %%D...
    pushd "%%D"
    javac *.java
    if errorlevel 1 (
        echo ERROR: Failed compiling %%D
        popd
        pause
        exit /b 1
    )
    popd
)
echo Compiling Tutorial 4 launcher...
javac Tutorial4App.java
if errorlevel 1 (
    echo ERROR: Failed compiling Tutorial4App.java
    pause
    exit /b 1
)
echo Starting main menu...
java Tutorial4App
if errorlevel 1 pause
