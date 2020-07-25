@echo off
if "%1"=="" goto loop
copy queen%1.in queen.in >nul
echo Problem Test %1
queen
fc queen.out queen%1.ans
pause
goto end
:loop
for %%i in (1 2 3 4 5 6 7 8 9 10) do call %0 %%i
:end
