import java.util.ArrayList;

public class CompactArrayList
{
  private ArrayList list = null;
  private int initialCapacity = 0;
  private float loadFactor = 1.0F;
  private int countValid = 0;
  
  public CompactArrayList()
  {
    this(10, 0.75F);
  }
  
  public CompactArrayList(int initialCapacity)
  {
    this(initialCapacity, 0.75F);
  }
  
  public CompactArrayList(int initialCapacity, float loadFactor)
  {
    this.list = new ArrayList(initialCapacity);
    this.initialCapacity = initialCapacity;
    this.loadFactor = loadFactor;
  }
  
  public void add(int index, Object element)
  {
    if (element != null) {
      this.countValid += 1;
    }
    this.list.add(index, element);
  }
  
  public boolean add(Object element)
  {
    if (element != null) {
      this.countValid += 1;
    }
    return this.list.add(element);
  }
  
  public Object set(int index, Object element)
  {
    Object oldElement = this.list.set(index, element);
    if (element != oldElement)
    {
      if (oldElement == null) {
        this.countValid += 1;
      }
      if (element == null) {
        this.countValid -= 1;
      }
    }
    return oldElement;
  }
  
  public Object remove(int index)
  {
    Object oldElement = this.list.remove(index);
    if (oldElement != null) {
      this.countValid -= 1;
    }
    return oldElement;
  }
  
  public void clear()
  {
    this.list.clear();
    this.countValid = 0;
  }
  
  public void compact()
  {
    if ((this.countValid <= 0) && (this.list.size() <= 0))
    {
      clear();
      return;
    }
    if (this.list.size() <= this.initialCapacity) {
      return;
    }
    float currentLoadFactor = this.countValid * 1.0F / this.list.size();
    if (currentLoadFactor > this.loadFactor) {
      return;
    }
    int dstIndex = 0;
    for (int srcIndex = 0; srcIndex < this.list.size(); srcIndex++)
    {
      Object wr = this.list.get(srcIndex);
      if (wr != null)
      {
        if (srcIndex != dstIndex) {
          this.list.set(dstIndex, wr);
        }
        dstIndex++;
      }
    }
    for (int i = this.list.size() - 1; i >= dstIndex; i--) {
      this.list.remove(i);
    }
  }
  
  public boolean contains(Object elem)
  {
    return this.list.contains(elem);
  }
  
  public Object get(int index)
  {
    return this.list.get(index);
  }
  
  public boolean isEmpty()
  {
    return this.list.isEmpty();
  }
  
  public int size()
  {
    return this.list.size();
  }
  
  public int getCountValid()
  {
    return this.countValid;
  }
}
