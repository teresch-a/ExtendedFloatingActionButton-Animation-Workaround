# ExtendedFloatingActionButton-Animation-Workaround
Unlike regular FloatingActionButton, ExtendedFAB has a bug with no animation for show() and hide() . This is a simple workaround until this bug is fixed.

                                          
#Bug / #Workaround

![alt text](https://i.ibb.co/17QfZ4p/bug.gif) ![alt text](https://i.ibb.co/rdhKpR8/workaround.gif)

```kotlin
    fun onScroll() {
        if (scrollDown) {
            //Workaround to return animation for EFAB
            val hideAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
            efab.startAnimation(hideAnimation)
            efab.hide()
        } else {
            //Workaround to return animation for EFAB
            val showAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)
            efab.startAnimation(showAnimation)
            efab.show()
        }
    }
```
