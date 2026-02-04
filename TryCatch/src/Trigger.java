public enum Trigger { // Used to differentiate trigger events to food items.
    add, // Whenver any item is added to the pot.
    added, // When *this* item is added to the pot.
    addFruit, // Whenever a fruit is added to the pot.
    eat // When the meal is eaten at the end of a turn. Also triggers for items not in the pot.
}
