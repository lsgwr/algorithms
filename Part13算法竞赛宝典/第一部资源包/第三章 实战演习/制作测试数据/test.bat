@echo off
if  "%1" == "" goto loop

copy sum%1.in sum.in > nul
echo Problem Test %1

time/t
sum
time/t

fc sum.out sum%1.ans
del sum.in
del sum.out

pause
goto end
:loop
for %%i in (1 2 3 4 5 6 7 8 9 10) do call %0 %%i
:end
