package com.example.demo.service;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.Lecture;
import com.example.demo.domain.member.Lecturer;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void delete(Item item){
        itemRepository.delete(item);
    }

    @Transactional
    public void updateItem(int price, String name,Long ItemId) {
        Item findItem = itemRepository.findOne(Item.class, ItemId);
        findItem.setPrice(price);
        findItem.setName(name);
    }

    @Transactional
    public void updateQuantity(Long bookId, int quantity) {
        Book book = itemRepository.findOne(Book.class, bookId);
        book.setStockQuantity(quantity);
    }


}
