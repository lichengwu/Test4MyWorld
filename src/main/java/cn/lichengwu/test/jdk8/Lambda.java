package cn.lichengwu.test.jdk8;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.*;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-03-22 上午12:38
 */
public class Lambda {

    @Test
    public void simpleTest() {
        List<Person> personList =
                Arrays.asList(new Person("name1", 18), new Person("name2", 24), new Person("name3", 20));
        personList.sort((a, b) -> a.getName().compareTo(b.getName()));
        System.out.println(personList);
    }

    @Test
    public void testOldSort() {
        List<Person> personList =
                Arrays.asList(new Person("name1", 18), new Person("name3", 24), new Person("name3", 20));
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.getName().compareTo(b.getName());
            }
        });
        System.out.println(personList);
    }

    @Test
    public void testFunction1() {
        ToStringFunction<Person> ps = (person) -> person.getName() + ":" + person.getAge();
        Person person = new Person("oliver", 25);
        System.out.println(ps.origin2String(person));
        System.out.println(ps.convert2String(person));
    }

    @Test
    public void testFunction2() {
        ToStringFunction<Person> ps = String::valueOf; //static method
        Person person = new Person("oliver", 25);
        System.out.println(ps.origin2String(person));
        System.out.println(ps.convert2String(person));
    }

    @Test
    public void testFunction3() {
        PersonFactory factory = Person::new; //constructor
        Person person = factory.create("oliver", 25);
        System.out.println(person);
    }

    @Test
    public void testFunction4() {
        ToIntFunction<Person> ps = Person::getAge;//instance method
        Person pp = new Person("oliver", 25);
        System.out.println(ps.applyAsInt(pp));
    }

    int df = 20;

    @Test
    public void testScope() {
        ToStringFunction<Person> ps = (person) -> {
            String s = person.getAge() + person.getName() + df;
            df = 21;
            return s;
        };
        df = 22;
    }

    @Test
    public void testPredicate() {
        Predicate<String> hasLength = (str) -> str.length() > 0;
        Predicate<String> isNull = (str) -> str == null;
        Predicate<String> isEmpty = isNull.negate().and(hasLength);
        System.out.println(isEmpty.test("")); //false
        System.out.println(isEmpty.test(null)); //false
    }

    @Test
    public void testFunction() {
        //        Function<String, String> s1 = String::valueOf;
        //        Function<String, String> s2 = String::valueOf;
        //        Function<String, String> all = s1.andThen(s2);
        //
        //        Supplier<Person> supplier = Person::new;
        //        Person person = supplier.get();
        //
        //        Consumer<Person> print = System.out::println;
        //        print.accept(person);

        Optional<Integer> opt1 = Optional.of(10);
        System.out.println(opt1.isPresent()); //true
        System.out.println(opt1.get()); //10
        System.out.println(opt1.filter((i) -> i > 11).isPresent()); //false
        System.out.println(opt1.filter((i) -> i > 11).get()); //java.util.NoSuchElementException: No value present

    }

    @Test
    public void testStream() {
        List<Integer> list = Arrays.asList(77, 1, 4, 5, 2, 77, 22);
        list.stream().distinct().filter((s) -> s > 10).sorted().forEach(System.out::println);
    }

    @Test
    public void testParallelStream() {
        int size = 1000000;
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add((int) (Math.random() * size));
        }
        long being = System.currentTimeMillis();
        long count1 = list.stream().filter((i) -> Math.sin(i) > 0).sorted().count();
        System.out.println("stream time used:" + (System.currentTimeMillis() - being));
        being = System.currentTimeMillis();
        long count2 = list.parallelStream().filter((i) -> Math.sin(i) > 0).sorted().count();
        Assert.assertEquals(count1, count2);
        System.out.println("parallelStream time used:" + (System.currentTimeMillis() - being));
    }


    @Test
    public void testReduce() {
        List<String> strings = Arrays.asList("1", "2", "3", "4");
        String reduce = strings.stream().reduce("", (a, b) -> a + "#" + b);
        System.out.println(reduce);
    }

}
