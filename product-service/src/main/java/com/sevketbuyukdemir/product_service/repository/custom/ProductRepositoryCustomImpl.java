package com.sevketbuyukdemir.product_service.repository.custom;

import com.sevketbuyukdemir.product_service.entity.Product;
import com.sevketbuyukdemir.product_service.entity.ProductAttribute;
import com.sevketbuyukdemir.product_service.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Product saveWithRelations(Product product) {
        Set<ProductCategory> managedCategories = new HashSet<>();
        for (ProductCategory c : product.getCategories()) {
            List<ProductCategory> existing = em.createQuery(
                            "SELECT cat FROM ProductCategory cat WHERE cat.name = :name", ProductCategory.class)
                    .setParameter("name", c.getName())
                    .getResultList();

            if (existing.isEmpty()) {
                em.persist(c);
                managedCategories.add(c);
            } else {
                managedCategories.add(existing.get(0));
            }
        }
        product.setCategories(managedCategories);
        Set<ProductAttribute> managedAttributes = new HashSet<>();
        for (ProductAttribute a : product.getAttributes()) {
            List<ProductAttribute> existing = em.createQuery(
                            "SELECT pa FROM ProductAttribute pa WHERE pa.attrType = :type AND pa.attrValue = :value", ProductAttribute.class)
                    .setParameter("type", a.getAttrType())
                    .setParameter("value", a.getAttrValue())
                    .getResultList();
            if (existing.isEmpty()) {
                em.persist(a);
                managedAttributes.add(a);
            } else {
                managedAttributes.add(existing.get(0));
            }
        }
        product.setAttributes(managedAttributes);
        return em.merge(product);
    }
}
