# CoolDatePicker

這是一個很簡單的日曆，主要架構分為CalendarView、TitleLayout、RecyclerView，這三種主要的框架。

依照圖片介紹一下：

<img width="300" height="500" src="https://github.com/Kuan-Wei-Kuo/CoolDatePicker/blob/master/art/Screenshot_2015-10-12-09-51-20.png?raw=true"/>

##TitleLayout

屬於上半部藍色部分，我提供了可以修改顏色與大小的方法。

##CalendarView

這部分與RecyclerView結合，也提供了顏色以及大小的方法。

#如何使用

##Step 1. 將CoolDatePicker加入你的Layout

```xml
<com.kuo.cooldatepicker_library.view.CoolDatePicker
        android:layout_width="match_parent"
        android:layout_height="match_parent"></com.kuo.cooldatepicker_library.view.CoolDatePicker>
```

##Step 2. 如果你想換顏色

因為這個專案我是使用Styleable作為主要更改，不想使用其他方式是因為消耗的資源太多，
假設我已經瀏覽了一堆CalendarView，如果要更改顏色勢必要重新將我的CalendarView繪製一遍，這不是個好方法。

```xml
  xmlns:android="http://schemas.android.com/apk/res-auto"
```

將上述的xml加入你的Layout設定內，後續我們便可以使用下面這些方法。

```xml
  <attr name="calendar_backgroundColor" format="color"/>
  <attr name="calendar_textColor" format="color"/>
  <attr name="calendar_textMonthColor" format="color"/>
  <attr name="calendar_textFoucsColor" format="color"/>
  <attr name="calendar_circleFoucsColor" format="color"/>

  <attr name="title_textYearColor" format="color"/>
  <attr name="title_textMonthColor" format="color"/>
  <attr name="title_textDayColor" format="color"/>
  <attr name="title_textDayOfWeekColor" format="color"/>
```

這部分我想應該不用解釋太多，所以就...自行嘗試吧！

##Step 3. 修改自體大小

這部分也是使用xml修改，不過修改部分可能很奇怪，其實是我有點懶惰啦！
我想讓Canvas適應螢幕大小產生出我們要的字體大小，所以使用dimens.xml可以快速的解決問題。

修改自體大小與CoolDatePicker所設定的大小有很大的關係，因為我懶惰沒有去做很多設定與更改，所以...
你就慢慢更改它吧！因為使用dimens.xml作更改，因此也不太需要害怕自適應螢幕的問題。

如果還是不能自適應...屙，這邊看你是要自己改還是回報我一下，我也將使用演算法將螢幕自體字適應大小算出來，
但可能沒辦法像現在可以改各種字體大小的自由度了。

```xml
  <dimen name="calendar_text_size">25dp</dimen>

  <dimen name="title_text_month">15dp</dimen>
  <dimen name="title_text_day">15dp</dimen>
  <dimen name="title_text_day_of_week">10dp</dimen>
  <dimen name="title_text_year">15dp</dimen>
```

這邊要怎麼修改？ 就直接改數字，不要怕，大力改，一直改...理論上是會自適應螢幕大小。

#結論

這應該算是我第一個正式一點點的Library，這個項目其實並不難，主要還是對於Canvas的了解，
其中我做了很多的嘗試，當然有很多更好的製作方式，但自行嘗試與研究總是好事情，對吧？

CalendarView這部分都是使用Canvas繪製，有人會想說怎麼不使用簡單的TableLayout或GridView製作呢？

##第一點，其實是RecyclerView與ViewHolder的問題，其實我是有點懶得去想為什麼？

主要也是當你每次加入一個View進去，其實並不是很好的方法，很容易Crash並且一直加入View，是閒手機不夠熱喔？
因為每個月份的數目不同，沒辦法去固定，沒辦法固定就得使用動態產生的方法，動態產生的方法又需要一直加入View，手機不好摧殘...

##第二點，其實很多好用的View與Canvas息息相關！

GitHub上有許多帥氣可愛美麗的控件，但他們與Canvas息息相關，扣的可深的帽子！
因為我們Canvas有很大的自由變化，開發者想幹嘛就幹嘛，只要你做得出來，在與OnTouch做結合，剩下的請自行腦補！

##第三點，如有問題請趕快告訴我，我會盡可能的修改與討論！

P.S. 程式碼有點亂，我還是會慢慢更新與優化的，不過也得有空閒...




