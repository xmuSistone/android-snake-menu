# android-snake-menu
imitate Tumblr's menu, dragging animations look like a snake

Several days ago, I installed the tumblr app into my genymotion device, and I was totally shocked by its amazing snake-menu animation which is quite smooth and fluent. Since I cannot clearly describe its visual effects, you’d better install this app and try it yourself.<br>
Ok, I am always the one who would like to imitate beautiful things. And I was terribly curious about how its animation is implemented. Let’s look at the visual effect imitated by myself:<br><br>
<td>
	 <img src="capture1.gif" width="290" height="485" />
	 <img src="capture2.gif" width="290" height="485" />
	 <img src="capture3.gif" width="290" height="485" />
</td>
<br>So, at the first sight, do you have any idea about how this animation appears?<br>
As you know, there are 6 imageviews: P1, P2, P3, P4, P5, P6, and P6 is the top-most imageview which is enabled to be dragged. You can easily figure it out that: P1 follows P2, P2 follows P3, P3 follows P4, P4 follows P5, P5 follows P6.<br>
First of all, I used ViewDragHelper to make P6 draggable.<br>
Then, ViewDragHelper.CallBack can tell us some information about P6’s status, such as dragging, released, and P6’s position change and so on. To lighten the parent ViewGroup’s job, I create a ViewTrackController to process P6’s status.<br>
Afterwards, ViewTrackController is responsible for dealing with all the linkage animations: every time P6’s position changes, causes P5’s target position changes. Every time P5’s position change causes P4’s target position changes. The rest imageviews can be done in the same manner.<br>
To make the animation pleasing to the eyes, facebook’s rebound sdk encounters with ViewTrackController. While reviewing the source code, you will find that each imageview has a SpringListener and a fllower-SpringListener. Obviously, P6’s follower is P5, P5’s follower is P4, P4’s follower is P3, etc… P6’s position change will notify P5’s follower-SpringListener, P5’s position change will notify P4’s follower-SpringListener, etc… That’s to say, P6’s position change leads the rest imageviews animate automatically.<br>
Finally, the animated snake-menu is implemented by this way.<br>
The source code is easy to read. If you have any problems or advice, please leave me a message on the issues, or contact my email: 120809170@qq.com. Besides, welcome for pull requests. 
####demo apk download
[apk download](SnakeMenu.apk) (right in this github repository)
