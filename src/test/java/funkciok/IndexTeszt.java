/*
 * Copyright 2015 Debreceni Egyetem, Informatikai Kar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package funkciok;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author STARLIGHT
 */
public class IndexTeszt {
    
    public IndexTeszt() {
    }
    
    
    @Test
    public void indexetSzámolRekeszbenNulla(){
        assertEquals(2, IndexSzamito.indexetSzámolRekeszben(0));
    }
    
    @Test
    public void indexetSzámolRekeszbenEgy(){
        assertEquals(0, IndexSzamito.indexetSzámolRekeszben(1));
    }
    
    @Test
    public void indexetSzámolRekeszbenKettő(){
        assertEquals(1, IndexSzamito.indexetSzámolRekeszben(2));
    }
    
    @Test
    public void indexetSzámolRekeszbenHárom(){
        assertEquals(2, IndexSzamito.indexetSzámolRekeszben(3));
    }
    
    @Test
    public void indexetSzámolRekeszbenKilenc(){
        assertEquals(2, IndexSzamito.indexetSzámolRekeszben(9));
    }
    
    @Test
    public void indexetSzámolTáblábanEgy(){
        assertEquals(0, IndexSzamito.indexetSzámolTáblában(1));
    }
    
    @Test
    public void indexetSzámolTáblábanNégy(){
        assertEquals(1, IndexSzamito.indexetSzámolTáblában(4));
    }
    
    @Test
    public void indexetSzámolTáblábanHét(){
        assertEquals(2, IndexSzamito.indexetSzámolTáblában(7));
    }

}
