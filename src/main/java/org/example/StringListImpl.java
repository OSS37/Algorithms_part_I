package org.example;

import javax.swing.text.Element;
import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] storage;
    private int size;

    public StringListImpl() {
        storage = new String[10];
    }

    public StringListImpl(int intSize) {
        storage = new String[intSize];
    }

    //добавление в конец
    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    //добавление по индексу
    @Override
    public String add(int index, String item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index == size) {
            storage[size++] = item;
            return item; //ворачиваем item, чтобы прервать метод
        }

        //Если index не равен size , то мы должны сдвинуть все элементы , которые находятся
        // на ячейке, которую мы передали индексом, а также все последующие на одну ячейку вправо
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;

        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item; // инжект данного item в данную ячейку по index
        return item; // если все успешно, вернется item, в противном случае выброшено исключение, т.к. не прошло валидацию
    }

    //удаление по item
    @Override
    public String remove(String item) {
        validateItem(item);

        //найти индекс элемента
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (index != size) {
            //затирание текущего элемента и вычисление кол-во элементов, которые нужно сместить влево
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }

        size--; //обнулили ячейку, не обнулив внутри нее значение
        return item;
    }
    //удаление по index
    @Override
    public String remove(int index) {
        validateIndex(index);

        String item = storage[index];

        if (index != size) {
            //затирание текущего элемента и вычисление кол-во элементов, которые нужно сместить влево
            System.arraycopy(storage, index + 1, storage, index, size - 1- index);
        }

        size--; //обнулили ячейку, не обнулив внутри нее значение
        return item;


    }

    //если метод indexOf вернул какой-либо индекс отличный от дефолтного, значит элемент находится
    //внутри нашей коллекции
    @Override
    public boolean contains(String item) {
        return indexOf(item) > -1;
    }

    // Если элемент не найден, то методы indexOf и lastIndexOf должны вернуть -1,
    // Если элемент найден indexOf ищет с нулевой ячейки, а lastIndexOf с size (последней ячейки)
    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            String s = storage[i];

            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            String s = storage[i];

            if (s.equals(item)) {
                return i;
            }
        }
        return -1;
    }
        //Валидирует индекс (проверить, что индекс попадает в допустимый интервал)
        @Override
        public String get ( int index){
        validateIndex(index);
            return storage[index];
        }


        // Вспомогательные методы
        @Override
        public boolean equals (StringList otherList){
            return Arrays.equals(this.toArray(), otherList.toArray()); //
        }

        @Override
        public int size () {
            return size;
        }

        @Override
        public boolean isEmpty () {
            return size == 0;
        }

        @Override
        public void clear () {
            size = 0;
        }


        // вернуть копию storage без учета пустых ячеек (незаполненных)
        @Override
        public String[] toArray () {
            return Arrays.copyOf(storage, size); //копия массива storage, размера size
        }

        // Проверка (подготовительный этап)
        private void validateItem (String item){
            if (item == null) {
                throw new nullItemException();
            }
        }

        private void validateSize () {
            if (size == storage.length) {
                throw new storageIsFullException();
            }
        }

        private void validateIndex ( int index){
            if (index < 0 || index > size) {
                throw new InvalidIndexException();
            }

        }
    }



