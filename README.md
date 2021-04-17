
## Answers to theoretical questions:
We didn't define any UX flow to let users edit a description on an existing TODO item.
Which UX flow will you define?
In your response notice the following:
1. how easy is it for users to figure out this flow in their first usage? (for example, a glowing button is more discoverable then a swipe-left gesture)
2. how hard to implement will your solution be?
3. how consistent is this flow with regular "edit" flows in the Android world?

I'd allow the users to add a description by clicking on a pencil icon which will appear for each item in the recycler view, and then opening a new activity for editing the item title and description.

Shouldn't be too complicated to implement - requires adding a new activity which should be able to report changes to the source activity which opened it upon finishing the edit, so that the original activity could update the item in the holder.

Re #3, that seems pretty consistent with the behavior of notes/todo apps like Google Keep / Microsoft ToDo.



I pledge the highest level of ethical principles in support of academic excellence.  I ensure that all of my work reflects my own abilities and not those of someone else.