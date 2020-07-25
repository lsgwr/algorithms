@echo off
if "%1"=="" goto loop
echo TEST
echo Test%1
copy skill%1.in skill.in >nul
time <enter
skill
time <enter
fc skill%1.out skill.out
pause
del skill.in
del skill.out
goto end
:loop
  for %%i in (1 2 3 4 5 6 7 8 9 10) do call %0 %%i
:end