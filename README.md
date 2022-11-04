<!--
*** Thanks for checking out this README Template. If you have a suggestion that would
*** make this better, please fork the repo and create a pull request or simply open
*** an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
***
***
***
*** To avoid retyping too much info. Do a search and replace for the following:
*** andreasgdp, Robot-hand-semester-2, AndreasGuldberg, andreasgdp@gmail.com
-->





<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
<!-- ABOUT THE PROJECT -->
## About The Project
<p align="center"> 
<img src="https://user-images.githubusercontent.com/39928082/200006428-1ced1bf0-f8ad-4f93-9450-786b471afd57.png" alt="Gripper" title="Gripper" width="30%" height="30%"/> 
</p>

### Abstract
In this project, we have worked to make a gripper that can be mounted on and integrated
with a UR5 robot. The gripper has been 3D modelled, 3D printed and mounted on a UR5 robot.
The gripper is controlled by the movements of a motor located in the center of the gripper. The engine receives signals
to go in each direction at varying speed from an electrical circuit which receives signals from
a C++ program running on a Raspberry Pi. We have made a URCaps extension for ours
grabs a UR5. We have made an XML-RPC server running on a Raspberry Pi to be able to
communicate with the URCaps extension. With this XML-RPC communication we have the opportunity to
to control the gripper from a UR program running on the UR5 robot with our URCaps extension.
We have also made a GUI running alongside the server on the Raspberry Pi that we can use
to control the gripper directly.


### Built With

* [C++]()
* [Pure manpower]()




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/andreasgdp/Robot-hand-semester-2.svg?style=flat-square
[contributors-url]: https://github.com/andreasgdp/Robot-hand-semester-2/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/andreasgdp/Robot-hand-semester-2.svg?style=flat-square
[forks-url]: https://github.com/andreasgdp/Robot-hand-semester-2/network/members
[stars-shield]: https://img.shields.io/github/stars/andreasgdp/Robot-hand-semester-2.svg?style=flat-square
[stars-url]: https://github.com/andreasgdp/Robot-hand-semester-2/stargazers
[issues-shield]: https://img.shields.io/github/issues/andreasgdp/Robot-hand-semester-2.svg?style=flat-square
[issues-url]: https://github.com/andreasgdp/Robot-hand-semester-2/issues
[license-shield]: https://img.shields.io/github/license/andreasgdp/Robot-hand-semester-2.svg?style=flat-square
[license-url]: https://github.com/andreasgdp/Robot-hand-semester-2/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/andreas-g-d-petersen-11707518b/
[product-screenshot]: images_readme/robot_hand.jpg
