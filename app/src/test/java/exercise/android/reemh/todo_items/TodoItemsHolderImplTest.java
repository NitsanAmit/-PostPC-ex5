package exercise.android.reemh.todo_items;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class TodoItemsHolderImplTest extends TestCase {

  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_addingMultipleTodoItem_then_callingListShouldHaveTheseItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("A");
    holderUnderTest.addNewInProgressItem("B");
    holderUnderTest.addNewInProgressItem("C");
    holderUnderTest.addNewInProgressItem("D");
    holderUnderTest.addNewInProgressItem("E");
    holderUnderTest.addNewInProgressItem("F");
    holderUnderTest.addNewInProgressItem("G");
    holderUnderTest.addNewInProgressItem("H");
    holderUnderTest.addNewInProgressItem("I");
    holderUnderTest.addNewInProgressItem("J");
    holderUnderTest.addNewInProgressItem("K");
    holderUnderTest.addNewInProgressItem("L");

    // verify
    assertEquals(12, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_deletingTodoItem_then_callingListShouldNotHaveThisItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());
    holderUnderTest.addNewInProgressItem("A");

    // test
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    holderUnderTest.deleteItem(holderUnderTest.getCurrentItems().get(0));

    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_deletingMultipleTodoItem_then_callingListShouldNotHaveTheseItems(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("A");
    holderUnderTest.addNewInProgressItem("B");
    holderUnderTest.addNewInProgressItem("C");
    holderUnderTest.addNewInProgressItem("D");

    // test
    List<TodoItem> currentItems = holderUnderTest.getCurrentItems();
    TodoItem a = currentItems.get(0);
    TodoItem b = currentItems.get(1);
    TodoItem c = currentItems.get(2);
    TodoItem d = currentItems.get(3);
    holderUnderTest.deleteItem(a);
    holderUnderTest.deleteItem(c);

    // verify
    assertEquals(2, holderUnderTest.getCurrentItems().size());
    assertEquals(b, holderUnderTest.getCurrentItems().get(0));
    assertEquals(d, holderUnderTest.getCurrentItems().get(1));
  }

  @Test
  public void when_modifyingCurrentItems_then_shouldntModifyOriginalList(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("A");
    holderUnderTest.addNewInProgressItem("B");

    // test
    List<TodoItem> currentItems = holderUnderTest.getCurrentItems();
    holderUnderTest.deleteItem(currentItems.get(0));

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    assertEquals(2, currentItems.size());
  }

  @Test
  public void when_callingSetItemProgressWithWrongIndex_then_shouldThrowIoobException(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("A");
    holderUnderTest.addNewInProgressItem("B");

    // test

    try {
      holderUnderTest.setItemProgress(3, true);
      fail("Should throw IndexOutOfBoundsException exception");
    } catch (IndexOutOfBoundsException e) {

    }
  }

  @Test
  public void when_callingSetItemProgress_then_shouldSetCorrectProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("A");
    holderUnderTest.addNewInProgressItem("B");

    // test
    holderUnderTest.setItemProgress(1, true);

    List<TodoItem> currentItems = holderUnderTest.getCurrentItems();
    assertFalse(currentItems.get(0).getCompleted());
    assertTrue(currentItems.get(1).getCompleted());
  }

  @Test
  public void when_callingMarkItemInProgressOnInProgressTodo_then_shouldDoNothing(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("A");
    holderUnderTest.addNewInProgressItem("B");

    // test
    assertFalse(holderUnderTest.getCurrentItems().get(0).getCompleted());
    holderUnderTest.setItemProgress(0, false);
    assertFalse(holderUnderTest.getCurrentItems().get(0).getCompleted());
  }

  @Test
  public void when_callingMarkItemInProgressOnCompletedTodo_then_shouldChangeProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("A");

    // test
    assertFalse(holderUnderTest.getCurrentItems().get(0).getCompleted());
    holderUnderTest.setItemProgress(0, true);
    assertTrue(holderUnderTest.getCurrentItems().get(0).getCompleted());
    holderUnderTest.setItemProgress(0, false);
    assertFalse(holderUnderTest.getCurrentItems().get(0).getCompleted());
  }

  @Test
  public void when_deletingItemNotInTheList_then_shouldDoNothing(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    holderUnderTest.addNewInProgressItem("A");
    holderUnderTest.addNewInProgressItem("B");
    holderUnderTest.addNewInProgressItem("C");

    // test
    TodoItem notInTheList = new TodoItem("D", true);
    assertEquals(3, holderUnderTest.getCurrentItems().size());
    holderUnderTest.deleteItem(notInTheList);
    assertEquals(3, holderUnderTest.getCurrentItems().size());
  }
}