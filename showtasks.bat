call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
goto fail

:openbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo There were errors.

:end
echo Script has finished.