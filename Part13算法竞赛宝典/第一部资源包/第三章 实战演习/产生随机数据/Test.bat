@echo off
:start
CreatRand
program1
program2

fc out1.txt out2.txt>result.txt
:end
goto start
