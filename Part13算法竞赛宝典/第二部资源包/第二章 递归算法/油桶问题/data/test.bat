@echo off
if "%1"=="" goto loop
copy oil%1.in oil.in >nul
echo Problem Test %1
oil
fc oil.out oil%1.ans
del oil.in
del oil.out
pause
goto end
:loop
for %%i in (1 2 3 4 5 6) do call %0 %%i
:end
