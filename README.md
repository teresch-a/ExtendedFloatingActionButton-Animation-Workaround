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
```kotlin
    //1 to Scale Up | 0 to Scale Down
    @BindingAdapter("scaleTo")
    fun ExtendedFloatingActionButton.scaleTo(scaleTo: Float) {
        if (scaleTo == 1f) visibility = View.VISIBLE

        ObjectAnimator.ofPropertyValuesHolder(
            this,
            PropertyValuesHolder.ofFloat("scaleX", scaleTo),
            PropertyValuesHolder.ofFloat("scaleY", scaleTo)
        ).apply {
            duration = 200
            addListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        if (scaleTo == 0f) visibility = View.INVISIBLE
                    }
                }
            )
        }.start()
    }
```
```kotlin
    //1 to Scale Up | 0 to Scale Down
    @BindingAdapter("scaleTo")
    fun ExtendedFloatingActionButton.scaleTo(scaleTo: Float) {
        if (scaleTo == 1f) visibility = View.VISIBLE

        animate().scaleX(scaleTo).scaleY(scaleTo).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (scaleTo == 0f) visibility = View.INVISIBLE
            }
        }).apply { duration = 200 }.start()
    }
```
