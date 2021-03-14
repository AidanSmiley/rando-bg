# Random Background Chooser for Windows Terminal

---

Hey wouldn't it be cool if I could have Windows Terminal choose a new background  
on every startup. So I made a simple Java program using the Simple JSON parser  
to do just that.  

### Sample Runs 

[![Sample 1](C:\Users\aidan\src\bgchooser\ex1.png)](https://ibb.co/mzRFfg5)
[![Sample 2](C:\Users\aidan\src\bgchooser\ex2.png)](https://ibb.co/tsTPvPd)
[![Sample 3](C:\Users\aidan\src\bgchooser\ex3.png)](https://ibb.co/bPFGc69)

## Some other notes  

Just because this was really only built for me because it seemed cool and like  
an interesting learning experience, the paths to images and the settings json file  
are hardcoded.  

Along with that at the moment it runs off of a text file that has  
a list of filenames in the picture folder that it uses to choose at random.  

If you do clone this to try for yourself make sure there are images in the RoamingState  
folder of Windows Terminal, aswell as a txt file that contains all the filenames. You will  
also need to change the `inFilePath` and `jsonPath` variables to whatever path it is  
on your system.

## TODO

- Make paths either relative or allow changing the paths on first run  
- Look through the pictures folder instead of using a text file
- Output json could look prettier?  


  






