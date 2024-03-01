@echo off
java -jar target\red-eye-frog-batch-1.0-SNAPSHOT.jar
if %ERRORLEVEL% neq 0 (
  echo:
  echo Fail.^(%ERRORLEVEL%^)
) else (
  echo:
  echo Success.
)
pause