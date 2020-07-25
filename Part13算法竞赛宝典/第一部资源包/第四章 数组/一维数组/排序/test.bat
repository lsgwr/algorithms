@echo off
if "%1"=="" goto loop
copy sort%1.in sort.in >nul
echo Problem Test %1
sort

fc sort.out sort%1.ans
del sort.in
del sort.out
pause
goto end
:loop
for %%i in (1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20) do call %0 %%i
:end
